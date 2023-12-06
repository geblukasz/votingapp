APPLICATION FLOW

1. Create a voter with a POST request to the endpoint /voters
   http://localhost:8080/swagger-ui/index.html#/user-controller/createVoter
2. Create a candidate with a POST request to the endpoint /candidates
   http://localhost:8080/swagger-ui/index.html#/candidate-controller/createCandidate
3. Create a vote with a POST request to the endpoint /votes. Please note that the voter and candidate ids are required.
   http://localhost:8080/swagger-ui/index.html#/vote-controller/createVote
4. Get the results with a GET request to the endpoint /votes/results
   http://localhost:8080/swagger-ui/index.html#/vote-controller/getResults

# (end)
