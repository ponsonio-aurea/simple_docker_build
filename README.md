# simple_docker_build
Docker Cloud PoC to test the APIs

### WBS (tasks)

* **Setup**
  * Setup account
  * Setup a build manually

* **Code**
  * Create a base code to call api
  * Create private registry
  * Trigger a build
  * Get LOGS
  * Notice when finish

* **Findings**
  * No formal api to create registry (repo)

* **Identified Risk and Mitigation**
  * No documented or formar api to create private registry : identify and use the api as part of the poc
  * Build cache?
  * Limmits
    [Docker Cloud Quotas](https://success.docker.com/article/does-docker-cloud-have-quotas)
