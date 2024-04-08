#!/bin/bash

# Connect to MongoDB and initiate the replica set
mongo --port 27017 <<EOF
sh.addShard("shard1ReplSet/shard1svr:27017")
sh.addShard("shard2ReplSet/shard2svr:27017")
sh.addShard("shard3ReplSet/shard3svr:27017")
EOF