DELIMITER $$
CREATE PROCEDURE `seckillcus`.`seckill`
	(in v_seckill_id BIGINT, in v_user_phone BIGINT, in v_kill_time TIMESTAMP, out r_result int)
	BEGIN
		DECLARE insert_count int DEFAULT 0;
		START TRANSACTION;
		insert ignore into seckillRecord(id,user_phone) values (v_seckill_id,v_user_phone);
		select row_count() into insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1; -- 重复秒杀
		ELSEIF (insert_count < 0) THEN
			ROLLBACK;
			set r_result = -2; -- sql未执行或错误
		ELSE
			update seckill set num = num - 1 where 
			id = v_seckill_id and num > 0
			and endTime > v_kill_time
			and startTime < v_kill_time;
			select row_count() into insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				set r_result = 0; -- 秒杀已结束
			ELSEIF (insert_count < 0) THEN
				ROLLBACK;
				set r_result = -2; -- sql未执行或错误
			ELSE
				COMMIT;
				set r_result = 1; -- 执行成功
			END IF;
		END IF;
	END;
$$
DELIMITER ;