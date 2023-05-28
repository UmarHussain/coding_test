package com.smallworld.entity;

import java.util.Objects;

public class IssueDTO {
  private Integer issueId;
  private Boolean issueSolved;
  private String issueMessage;

  public Integer getIssueId() {
    return issueId;
  }

  public void setIssueId(Integer issueId) {
    this.issueId = issueId;
  }

  public Boolean getIssueSolved() {
    return issueSolved;
  }

  public void setIssueSolved(Boolean issueSolved) {
    this.issueSolved = issueSolved;
  }

  public String getIssueMessage() {
    return issueMessage;
  }

  public void setIssueMessage(String issueMessage) {
    this.issueMessage = issueMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IssueDTO issue = (IssueDTO) o;
    return Objects.equals(getIssueId(), issue.getIssueId())
        && Objects.equals(getIssueSolved(), issue.getIssueSolved())
        && Objects.equals(getIssueMessage(), issue.getIssueMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIssueId(), getIssueSolved(), getIssueMessage());
  }
}
