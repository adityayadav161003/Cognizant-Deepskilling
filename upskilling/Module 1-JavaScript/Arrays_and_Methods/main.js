let list = [{name: "Tech Meetup", category: "Tech"}, {name: "Concert", category: "Music"}];
list.push({name: "Salsa", category: "Dance"});
let musicEvents = list.filter(e => e.category === "Music");
let formatted = list.map(e => "Workshop on " + e.name);
console.log("Music:", musicEvents);
console.log("Formatted:", formatted);
