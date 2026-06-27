SELECT event_id, COUNT(*) as reg_count FROM Registrations GROUP BY event_id ORDER BY reg_count DESC LIMIT 3;
