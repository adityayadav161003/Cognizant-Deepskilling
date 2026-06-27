SELECT speaker_name, COUNT(*) as sessions_count FROM Sessions GROUP BY speaker_name HAVING sessions_count > 1;
