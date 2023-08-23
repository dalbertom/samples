# Joins
```
sqlite> select * from jenkins where job in (select job from jenkins_job_per_file where file = 'server/process/analytics/paapi.k');
_lib-master|2379052
analytics-api-daily|23538245
analytics-master|2620815
analytics-reset-daily|7710588
backend-daily|10515454
kmigration-branch|3601529
process-daily|14385624
process-history-kafka-branch|8211819
process-history-kafka-master|8545527
server-unit|4662673
```

```
sqlite> select jenkins.job, jenkins.duration from jenkins inner join jenkins_job_per_file on jenkins.job=jenkins_job_per_file.job where jenkins_job_per_file.file='server/process/analytics/paapi.k' order by jenkins.duration asc;
_lib-master|2379052
analytics-master|2620815
kmigration-branch|3601529
server-unit|4662673
analytics-reset-daily|7710588
process-history-kafka-branch|8211819
process-history-kafka-master|8545527
backend-daily|10515454
process-daily|14385624
analytics-api-daily|23538245
```
