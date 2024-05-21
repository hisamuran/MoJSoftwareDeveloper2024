# MoJSoftwareDeveloper2024

To try this out, use the tests inside src/test and run them from your IDE (Visual Studio Code, for example)
The tests are built using Junit and cover several cases.
Trying to build the whole gradle app will fail as I focused on the challenge requirements. This code could be used to build upon it and expand the existing functionality.

Design pattern used: MVC
Language: Java 17 and SpringBoot 3


# Oyster Card Coding Test 
First please take some time to read the instructions, ask any questions and state any assumptions.
We'd like you to model the following public transport fare system, which is a limited version of London’s Oyster card system. At the end of the test, you should aim to demonstrate a user loading a card with £30, taking the following trips, and then viewing the balance. 
- Tube: Holborn to Earl’s Court 
- 328 bus from Earl’s Court to Chelsea 
- Tube: Earl’s Court to Hammersmith 
We don’t expect a full solution in one hour, but are interested in your thought processes, how you communicate your ideas, your code structure and your approach to testing. Please treat this as a remote pairing exercise.
# Operation 
When the user passes through the inward barrier at the station, their oyster card is charged the maximum fare. 
When they pass out of the barrier at the exit station, the fare is calculated and the maximum fare transaction removed and replaced with the real transaction (in this way, if the user doesn’t swipe out, they are charged the maximum fare). 
Similarly, if they swipe out without swiping in, they are charged the maximum fare. 
They will be refused entry if the balance on the card is not at least the minimum fare for that station. 
All bus journeys are charged at the same price. 
# Data 
For the purposes of this test use the following data: 
Stations and zones: 
- Station Zones 
Holborn 1 
Earl’s Court 1,2 
Wimbledon 3 
Hammersmith 2 
Fares: 
Anywhere in Zone 1 £2.50 
Any one zone outside zone 1 £2.00 
Any two zones including zone 1 £3.00 
Any two zones excluding zone 1 £2.25 
Any three zones £3.20 
Any bus journey £1.80 
The maximum possible fare is therefore £3.20.
