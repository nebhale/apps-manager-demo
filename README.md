```
curl -k -X POST \
  <HOST>/health/composite/[alpha|bravo]/mutate\?status\=DOWN \
  -H "Content-Type: application/json" \
  -d '{ "key": "value" }'
```
