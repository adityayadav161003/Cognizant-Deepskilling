const getDetails = ({name, date, ...rest}) => {
    console.log(`Name: ${name}, Date: ${date}`);
};
getDetails({name: "ES6 Event", date: "2026-12", location: "Hall A"});
