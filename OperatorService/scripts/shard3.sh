#!/bin/bash

# Connect to MongoDB and initiate the replica set
mongo --port 27017 <<EOF
rs.initiate({
  _id: "shard3ReplSet",
  members: [{ _id: 0, host: "shard3svr:27017" }]
})
EOF