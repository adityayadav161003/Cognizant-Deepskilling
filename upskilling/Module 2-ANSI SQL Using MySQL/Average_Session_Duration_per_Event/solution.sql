SELECT event_id, AVG((julianday(end_time) - julianday(start_time)) * 1440) as avg_duration_minutes FROM Sessions GROUP BY event_id;
