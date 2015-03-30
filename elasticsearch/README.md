# Installation
```
brew install elasticsearch
```

# Run
```
elasticsearch
```

Override parameters
```
elasticsearch --cluster.name my_cluster_name --node.name my_node_name
```

# Cluster Health
```
curl ‘localhost:9200/_cat/health?v’
```
* Green: Cluster is fully functional
* Yellow: All data is available but some replicas are not yet allocated
* Red: Some data is not available

Get a list of nodes:
```
curl ‘localhost:9200/_cat/nodes?v’
```

# List all Indexes
```
curl ‘localhost:9200/_cat/indices?v’
```

# Create an Index
```
curl -XPUT ‘localhost:9200/customer?pretty’
curl ‘localhost:9200/_cat/indices?v’
```

# Index and Query a Document
```
curl -XPUT 'localhost:9200/customer/external/1?pretty' -d'
{
  "name": "John Doe"
}'
```
Elasticsearch does not require you to explicitly create an index first before you can index documents into it. It will automatically create the customer index if it didn’t already exist beforehand.

Retrieve what was indexed:
```
curl -XGET ‘localhost:9200/customer/external/1?pretty’
```

# Delete an Index
```
curl -XDELETE ‘localhost:9200/customer?pretty’
curl ‘localhost:9200/_cat/indices?v’
```

Pattern:
`curl -X<REST Verb> <Node>:<Port>/<Index>/<Type>/<ID>`

# Modifying Your Data
```
curl -XPUT ‘localhost:9200/customer/external/1?pretty’ -d ‘
{
  “name”: “John Doe”
}’
curl -XPUT ‘localhost:9200/customer/external/1?pretty’ -d ‘
{
  “name”: “Jane Doe”
}’
```
When indexing, the ID part is optional. If not specified, Elasticsearch will generate a random ID and then use it to index the document.
```
curl -XPOST ‘localhost:9200/customer/external?pretty’ -d ‘
{
  “name”: “Jane Doe”
}’
```
Using POST instead of PUT since we are not specifying the ID

# Updating Documents
```
curl -XPOST ‘localhost:9200/customer/external/1/_update?pretty’ -d ‘
{
  “doc”: { “name”: “Jane Doe” }
}’

curl -XPOST ‘localhost:9200/customer/external/1/_update?pretty’ -d ‘
{
  “doc”: { “name”: “Jane Doe”, “age”: 20 }
}’
```

# Deleting Documents
Delete previous customer with ID of 2:
```
curl -XDELETE ‘localhost:9200/customer/external/2?pretty’
```

Delete multiple documents that match a query condition.
```
curl -XDELETE ‘localhost:9200/customer/external/_query?pretty’ -d ‘
{
  “query”: { “match”: { “name”: “John” } }
}’
```

# Batch Processing
Index two documents in one operation
```
curl -XPOST ‘localhost:9200/customer/external/_bulk?pretty’ -d ‘
{“index”:{“_id”:”1”}}
{“name”:”John Doe”}
{“index”:{“_id”:”2”}}
{“name”:”Jane Doe”}}
```

Update first document, delete the second document
```
curl -XPOST ‘localhost:9200/customer/external/_bulk?pretty’ -d ‘
{“update”:{“_id”:”1”}}
{“doc”: {“name”: “John Doe becomes Jane Doe”}}
{“delete”:{“_id”:”2”}}
‘
```


