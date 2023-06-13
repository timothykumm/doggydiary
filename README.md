# DoggyDiary

A comprehensive web application that provides features for dog owners to track and manage their pet's life. Powered by a self-trained image recognition model, the app can accurately detect the breed of a dog. In addition to breed detection, the app integrates image text-extraction that allows users to scan, digitize and store documents such as veterinary records, vaccination certificates, and medical reports. The document scanning feature leverages Chat GPT to efficiently process the document.

![1](https://github.com/timothykumm/doggydiary/assets/27158937/e86ca515-d064-4a88-9fb7-b92e31b191bf)
![2](https://github.com/timothykumm/doggydiary/assets/27158937/250c676c-90f7-485c-a7a5-8fd7f7af8ea1)
![3](https://github.com/timothykumm/doggydiary/assets/27158937/4d245eec-785c-44d0-8286-941395df5df1)
![4](https://github.com/timothykumm/doggydiary/assets/27158937/f36dc92e-e88b-4c68-92f9-4b94bea8cad8)


---
## __How to setup__
1. Pull the [mysql](https://hub.docker.com/_/mysql/), [minio](https://hub.docker.com/r/minio/minio) and the [dog-classification](https://hub.docker.com/r/timmycode/dog-breed-classification/tags) docker image
2. Open docker-compose.yml and modify the minio volume path from `C:/docker/minio:/data` to `<custom_path>:/data` so that images can be stored. Replace `<custom_path>` with a path on your local system.
3. Start all container with `docker-compose up`
4. Open the minioclient panel (standard is [http://localhost:9001](http://localhost:9001)), login with (__User__: minioadmin, __PW__: minioadmin), open the bucket browser, create a bucket named "dogimages" and set its accesibility to public.
5. Start the frontend with `ng serve` and the backend with `gradle bootrun`
6. You should now be able to use the web-application via [http://localhost:4200](http://localhost:4200)

    <sub> If you have erros regarding cors policy, you should use this [chrome plugin](https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=de) </sub>

