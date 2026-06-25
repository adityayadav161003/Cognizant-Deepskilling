function Event(name, seats) {
    this.name = name;
    this.seats = seats;
}
Event.prototype.checkAvailability = function() {
    return this.seats > 0;
};
let e = new Event("Tech Workshop", 15);
console.log(Object.entries(e));
console.log("Available: " + e.checkAvailability());
