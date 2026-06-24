async function fetchEvents() {
    return new Promise((resolve) => {
        setTimeout(() => resolve([{name: "Async Event 1"}, {name: "Async Event 2"}]), 1000);
    });
}
async function run() {
    console.log("Loading...");
    let data = await fetchEvents();
    console.log("Events loaded:", data);
}
run();
