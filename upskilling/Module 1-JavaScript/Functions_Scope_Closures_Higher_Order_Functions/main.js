function makeRegistrationTracker() {
    let count = 0;
    return function() {
        count++;
        return count;
    }
}
const trackTechReg = makeRegistrationTracker();
console.log("Registrations: " + trackTechReg());
console.log("Registrations: " + trackTechReg());
