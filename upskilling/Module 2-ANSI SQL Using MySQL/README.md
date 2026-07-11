# Module 2 - ANSI SQL Using MySQL Up-skilling

This module contains database query exercises written in ANSI SQL for MySQL, analyzing events, speakers, sessions, feedback ratings, user sign-ups, and resource utilization.

## Core Queries Covered

1. **User Signups & Activity**
   - **Daily New User Count** (`Daily_New_User_Count`): Daily aggregate of new user registrations.
   - **Inactive Users** (`Inactive_Users`): Finding accounts with zero registrations.
   - **Unregistered Active Users** (`Unregistered_Active_Users`): Spotting users who log in but do not book events.

2. **Event & Session Analysis**
   - **Sessions per Upcoming Event** (`Sessions_per_Upcoming_Event`): Counting scheduled segments per event.
   - **Events Without Sessions** (`Events_Without_Sessions`): Checking for events missing session details.
   - **Event with Maximum Sessions** (`Event_with_Maximum_Sessions`): Querying the most session-heavy events.
   - **Average Session Duration per Event** (`Average_Session_Duration_per_Event`): Calculating the mean length of event segments.
   - **Event Session Time Conflict** (`Event_Session_Time_Conflict`): Detecting double-booked speaker slots or overlapping session times.

3. **Ratings & Feedback**
   - **Average Rating per City** (`Average_Rating_per_City`): Aggregating feedback ratings grouped by location.
   - **Top Rated Events** (`Top_Rated_Events`): Retrieving events with the highest average satisfaction ratings.
   - **Low Feedback Alerts** (`Low_Feedback_Alerts`): Flags for events scoring below critical thresholds.
   - **Feedback Gap** (`Feedback_Gap`): Identifying the delta between registered attendees and actual feedback submittals.

4. **Resource & Logistics**
   - **Event Resource Summary** (`Event_Resource_Summary`): Summarizing equipment, venue space, or media assets per event.
   - **Resource Availability Check** (`Resource_Availability_Check`): Verification checks for conflicts in physical assets.
   - **Duplicate Registrations Check** (`Duplicate_Registrations_Check`): Identifying duplicate attendee records.
