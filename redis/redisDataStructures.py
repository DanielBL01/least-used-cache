import redis
client = redis.Redis(host='localhost', port=6379, db=0)

''' How to use Redis Hash '''

# Set Key (2nd arg) to Value (3rd arg) within Hash (1st arg)
client.hset("whoami", "Name", "Daniel Lee")
client.hset("whoami", "Job", "Software Engineer")

# Get Value from Key (2nd arg) within Hash (1st arg)
print(client.hget("whoami", "Name"))
print(client.hget("whoami", "Job"))

# Get Key, Value within Hash (1st arg) - returns a Dictionary
print(client.hgetall("whoami"))

# Delete *Keys from Hash (1st arg)
client.hdel("whoami", "Name", "Job")

# Hash is now an empty dictionary {}
print(client.hgetall("whoami"))

''' How to use Redis List (Doubly Linked List) '''

# Push *Values to head of the list (1st arg) - Stephen would be first item of the List
client.lpush("friends", "Mike", "Josh", "Bob", "Stephen")

# Remove and return first item of the list (1st arg)
print(client.lpop("friends"))
print(client.lpop("friends"))
print(client.lpop("friends"))
print(client.lpop("friends"))
