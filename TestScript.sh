
#dates in testDB

# 3 movies in this timespan
# 27.04.2022, 15:00:00
# 27.04.2022, 17:15:00

# 1 movie
# 29.04.2022, 17:15:00
# 29.04.2022, 20:15:00

# see movies in time span, expected free movies which start 27.04.2022, 15:00:00
# url format http://localhost:8080/movies/{startTimestamp}/{endTimestamp}
curl -v http://localhost:8080/movies/1651064400000/1651143600000

#show movie details
curl -v http://localhost:8080/movieDetails/21

# okay reservation
curl  http://localhost:8080/makeReservation/  -H "Content-Type: application/json"  -d @JSONS/reservation1.txt -X POST -v

# you cant reserve a taken seat
curl  http://localhost:8080/makeReservation/  -H "Content-Type: application/json"  -d @JSONS/reservation1.txt -X POST -v

#invalid placement reservation
curl  http://localhost:8080/makeReservation/  -H "Content-Type: application/json"  -d @JSONS/reservation2.txt -X POST -v

#seats have to exist
curl  http://localhost:8080/makeReservation/  -H "Content-Type: application/json"  -d @JSONS/reservation3.txt -X POST -v