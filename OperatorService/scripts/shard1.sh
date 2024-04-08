#!/bin/bash

# Connect to MongoDB and initiate the replica set
mongo --port 27017 <<EOF
rs.initiate({
  _id: "shard1ReplSet",
  members: [{ _id: 0, host: "shard1svr:27017" }]
})
EOF