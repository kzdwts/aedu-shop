-- 查询活动列表
SELECT * FROM seckill_activity WHERE end_time > NOW() ORDER BY start_time ASC LIMIT 5;