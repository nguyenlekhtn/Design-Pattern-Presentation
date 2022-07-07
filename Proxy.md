# Proxy

## Intent

* Is a structural design pattern
* Let you provide a substitute or placeholder for another object

## Problem

**Example:** A massive object that consumes of system resources. You need it from time to time, but not always.

<figure>
  <img src="./images/problem-en.png">
  <figcaption>
      Database queries can be really slow.
  </figcaption>
</figure>

**Approach:**
* Lazy initialization?
* Put this code directly into our object's class

## Solution
A new proxy class with the same interface as an original service object.

<figure>
  <img src="./images/solution-en.png">
  <figcaption>
      The proxy disguises itself as a database object. It can handle lazy initialization and result caching without the client or the real database object even knowing.
  </figcaption>
</figure>

Benefit?

## Real-World Analogy

<figure>
  <img src="./images/live-example.png">
  <figcaption>
      Credit cards can be used for payments just the same as cash.
  </figcaption>
</figure>


## Structure

<img src="./images/structure-indexed.png">

##  Pseudocode

This example illustrates how the Proxy pattern can help to introduce lazy initialization and caching to a 3rd-party YouTube integration library.

<figure>
  <img src="./images/example.png">
  <figcaption>
      Caching results of a service with a proxy.
  </figcaption>
</figure>

The library provides us with the video downloading class. However, it’s very inefficient. If the client application requests the same video multiple times, the library just downloads it over and over, instead of caching and reusing the first downloaded file.

The proxy class implements the same interface as the original downloader and delegates it all the work. However, it keeps track of the downloaded files and returns the cached result when the app requests the same video multiple times.

```
interface ThirdPartyYouTubeLib is
    method listVideos()
    method getVideoInfo(id)
    method downloadVideo(id)
    
class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib is
    method listVideos() is
        // Send an API request to YouTube.

    method getVideoInfo(id) is
        // Get metadata about some video.

    method downloadVideo(id) is
        // Download a video file from YouTube.

class CachedYouTubeClass implements ThirdPartyYouTubeLib is
    private field service: ThirdPartyYouTubeLib
    private field listCache, videoCache
    field needReset

    constructor CachedYouTubeClass(service: ThirdPartyYouTubeLib) is
        this.service = service

    method listVideos() is
        if (listCache == null || needReset)
            listCache = service.listVideos()
        return listCache

    method getVideoInfo(id) is
        if (videoCache == null || needReset)
            videoCache = service.getVideoInfo(id)
        return videoCache

    method downloadVideo(id) is
        if (!downloadExists(id) || needReset)
            service.downloadVideo(id)
            
class YouTubeManager is
    protected field service: ThirdPartyYouTubeLib

    constructor YouTubeManager(service: ThirdPartyYouTubeLib) is
        this.service = service

    method renderVideoPage(id) is
        info = service.getVideoInfo(id)
        // Render the video page.

    method renderListPanel() is
        list = service.listVideos()
        // Render the list of video thumbnails.

    method reactOnUserInput() is
        renderVideoPage()
        renderListPanel()
        
class Application is
    method init() is
        aYouTubeService = new ThirdPartyYouTubeClass()
        aYouTubeProxy = new CachedYouTubeClass(aYouTubeService)
        manager = new YouTubeManager(aYouTubeProxy)
        manager.reactOnUserInput()
```

## Applicability

* Lazy initialization (virtual proxy)
* Access control
* Local execution of a remote service (remote proxy)
* Logging requests
* Caching request results (caching proxy)
* Smart reference

## Pros and Cons

**Pros**
* You can control the service object without clients knowing about it.
* You can manage the lifecycle of the service object when clients don’t care about it.
* The proxy works even if the service object isn’t ready or is not available.
* Open/Closed Principle. You can introduce new proxies without changing the service or clients.

**Cons**
* The code may become more complicated since you need to introduce a lot of new classes.
* The response from the service might get delayed.

## Code Examples
