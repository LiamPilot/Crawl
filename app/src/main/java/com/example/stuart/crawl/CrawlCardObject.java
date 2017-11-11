package com.example.stuart.crawl;

public class CrawlCardObject {
  private String crawlName;

  CrawlCardObject(String crawlName) {
    this.crawlName = crawlName;
  }

  public String getCrawlName() {
    return crawlName;
  }

  public void setCrawlName(String crawlName) {
    this.crawlName = crawlName;
  }
}
