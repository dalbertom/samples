# Flight School
* Clone repository `git clone https://github.com/dalbertom/flight-school /tmp/flight-school`
* Target concourse `fly -t ci login -c http://$CONCOURSE_EXTERNAL_URL`
  * username: concourse
  * password: changeme

* Copy build.yml file with `cp * /tmp/flight-school`
* Execute test with:
```
cd /tmp/flight-school
fly -t ci execute -c build.yml
cd -
```

## Starting a Pipeline
* Copy the pipeline with `cp pipeline.yml /tmp/flight-school`
* Upload the pipeline with `fly -t ci set-pipeline -p flight-school -c pipeline.yml`
