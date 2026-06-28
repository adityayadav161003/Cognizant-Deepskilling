SELECT * FROM Events WHERE event_id NOT IN (SELECT event_id FROM Resources);
