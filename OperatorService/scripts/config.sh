#!/bin/bash

# Connect to MongoDB and initiate the replica set
mongo --port 27019 <<EOF
rs.initiate({
  _id: "configReplSet",
  configsvr: true,
  members: [
    { _id: 0, host: "configsvr1:27019" },
    { _id: 1, host: "configsvr2:27020" },
    { _id: 2, host: "configsvr3:27021" }
  ]
})
EOF

