SELECT user_id, event_id, COUNT(*) FROM Registrations GROUP BY user_id, event_id HAVING COUNT(*) > 1;
