SELECT event_id, COUNT(*) as session_count FROM Sessions GROUP BY event_id ORDER BY session_count DESC LIMIT 1;
