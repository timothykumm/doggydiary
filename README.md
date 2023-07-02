# DoggyDiary

A comprehensive web application that provides features for dog owners to track and manage their pet's life. Powered by a self-trained image recognition model, the app can accurately detect the breed of a dog. In addition to breed detection, the app integrates image text-extraction that allows users to scan, digitize and store documents such as veterinary records, vaccination certificates, and medical reports. The document scanning feature leverages Chat GPT to efficiently process the document.

![1-copy](https://github.com/timothykumm/doggydiary/assets/27158937/53a4e6c1-8fdb-44f7-985a-a9933abb404b)
![2-copy](https://github.com/timothykumm/doggydiary/assets/27158937/4722a793-6a01-45db-a741-2eebd2a110cc)
![3-copy](https://github.com/timothykumm/doggydiary/assets/27158937/5c1b8f05-2202-43ca-b1c6-b49d8f19bab9)
![4-copy](https://github.com/timothykumm/doggydiary/assets/27158937/ac2a5e61-1b50-4a4c-80c5-d866cd55f5e8)
![5-copy](https://github.com/timothykumm/doggydiary/assets/27158937/f2815c36-d66b-46ee-8549-576184284de3)
![6-copy](https://github.com/timothykumm/doggydiary/assets/27158937/6a535218-73e8-488e-98e3-73b2cfe8e3ee)

---
## __How to setup__
1. Pull the [mysql](https://hub.docker.com/_/mysql/), [minio](https://hub.docker.com/r/minio/minio) and the [dog-classification](https://hub.docker.com/r/timmycode/dog-breed-classification/tags) docker image
2. Open docker-compose.yml and modify the minio volume path from `C:/docker/minio:/data` to `<custom_path>:/data` so that images can be stored. Replace `<custom_path>` with a path on your local system.
3. Start all container with `docker-compose up`
4. Open the minioclient panel (standard is [http://localhost:9001](http://localhost:9001)), login with (__User__: minioadmin, __PW__: minioadmin), open the bucket browser, create a bucket named "dogimages" and set its accesibility to public.
5. Start the frontend with `ng serve` and the backend with `gradle bootrun`
6. You should now be able to use the web-application via [http://localhost:4200](http://localhost:4200)

    <sub> If you have erros regarding cors policy, you should use this [chrome plugin](https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=de) </sub>

