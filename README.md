# simple_docker_build
Docker Cloud PoC to test the APIs

### WBS (tasks)

* **Setup**
  * Setup account
  * Setup a build manually
  (https://cloud.docker.com/swarm/ponsonio/repository/docker/ponsonio/poc/general)

* **Code**
  * Create a base code to call api
  * Create private registry
  * Trigger a build
  * Get LOGS
  * Notice when finish

* **Findings**
  * No formal api to create registry (repo)
  * Build cache: a build cache should be disable

* **Identified Risk and Mitigation**
  * Build process (and related operations) can take quite some time : an un-attended or even driven mechanism must be put in place)
  * DCS : presents a set of statuses:  those must have equivalent status on devspaces
  * No documented or formal api to create private registry : identify and use the api as part of the poc
  * Limits: Even paid accounts has limmits, so this must be handled on devspaces.
    * [Docker Cloud Quotas](https://success.docker.com/article/does-docker-cloud-have-quotas)
