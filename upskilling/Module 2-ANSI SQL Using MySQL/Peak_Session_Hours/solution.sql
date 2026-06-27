SELECT event_id, COUNT(*) FROM Sessions WHERE strftime('%H:%M:%S', start_time) >= '10:00:00' AND strftime('%H:%M:%S', end_time) <= '12:00:00' GROUP BY event_id;
