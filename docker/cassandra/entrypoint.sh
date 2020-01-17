#!/bin/bash

/usr/sbin/sshd -D &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start sshd: $status"
  exit $status
fi

cassandra -f -R &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start cassandra: $status"
  exit $status
fi

while sleep 60; do
  tail -f /var/log/cassandra/system.log
done
