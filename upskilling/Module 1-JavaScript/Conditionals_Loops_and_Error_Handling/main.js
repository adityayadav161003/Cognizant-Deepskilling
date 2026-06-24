let events = [
    { name: "Tech Meetup", seats: 10, past: false },
    { name: "Art Gallery", seats: 0, past: false },
    { name: "Concert", seats: 5, past: true }
];
events.forEach(e => {
    try {
        if (e.past) throw new Error(`${e.name} has already passed.`);
        if (e.seats <= 0) throw new Error(`${e.name} is fully booked.`);
        console.log(`Displaying event: ${e.name}`);
    } catch(err) {
        console.warn("Skipped event: " + err.message);
    }
});
