#!/bin/bash

# Connect to MongoDB and initiate the replica set
mongo --port 27017 <<EOF
rs.initiate({
  _id: "shard2ReplSet",
  members: [{ _id: 0, host: "shard2svr:27017" }]
})
EOF