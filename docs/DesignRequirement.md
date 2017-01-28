Design Requirement              

Purpose
	A centralized scheduling system with multi-featured functionalities that can solve various real time configuration and scheduling issues.  The Scheduler REST API helps manage the communication for these actions. 
	
Entities
	Highlevel entities involved in scheduling engines are as follows

Job Entity
JobName – Name of the Job Scheduled.
serviceOwner – Owner of the Scheduler job.
status – Status of the Scheduler job.
description – Description of the Scheduler job.
schedulerType – Scheduling types (once, daily, weekly, monthly).
startTime - Requested start time of the job run.
endTime - Actual end time of the job run.
progress – Percentage of scheduler job completed based on the counts.
count – count of execution based on the Scheduler type selected.
             If daily - no of days, weekly - no of weeks, monthly – no of months.
dayOfWeek – Day of the week for execution when Scheduler type is weekly.
dayOfMonth - Day of the week for execution when Scheduler type is monthly.
dockerImage – Docker image name.
location – Exection location. 
JobId - Unique identifier of the Job.
taskLists – Execution tasks associated to the Job.
params – Additional Params associated to the Job 
isRetryEnabled – Defines an execution to be retried on error cases.
retryCounts – Defines no of time to be retried on error cases.

Task Entity
JobId – Reference to the Job.
startTime -  Actual start time of task execution.
endTime -  Actual end time of task execution.
status – Status of the task execution.
parentTaskId – Parent task id on retries
duration – Duration of task execution.
logs – Logs associated to the task.
taskId - Unique identifier of the task.
additionalInfo – Additional information about the task.



Log Entity
logId – Unique identifier of the log entry
resourceType – Type of resource the log used for.
description – Actual log with its details.

Scheduler Type
once – One time scheduling with no recursion
daily – frequency of recurring daily
weekly – frequency of recurring weekly
monthly – frequency of recurring monthly

Resource Type
task – Task related resources
job – Job related resources


Technologies planned 
	Java, SpringBoot, Cassandra, Hateoas