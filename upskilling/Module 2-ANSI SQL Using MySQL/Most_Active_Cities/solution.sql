SELECT city, COUNT(DISTINCT user_id) as registrations_count FROM Registrations r JOIN Events e ON r.event_id = e.event_id GROUP BY city ORDER BY registrations_count DESC LIMIT 5;
