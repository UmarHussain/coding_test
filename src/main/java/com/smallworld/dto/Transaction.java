package com.smallworld.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/** Class to Map the TransactionDTO as-is from json which is then converted to */
public class Transaction {
  private Integer mtn;
  private Double amount;
  private String senderFullName;
  private Integer senderAge;
  private String beneficiaryFullName;
  private Integer beneficiaryAge;

  // IssueDTO Data
  private Integer issueId;
  private Boolean issueSolved;
  private String issueMessage;

  public Integer getMtn() {
    return mtn;
  }

  public void setMtn(Integer mtn) {
    this.mtn = mtn;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getSenderFullName() {
    return senderFullName;
  }

  public void setSenderFullName(String sendFullName) {
    this.senderFullName = sendFullName;
  }

  public Integer getSenderAge() {
    return senderAge;
  }

  public void setSenderAge(Integer senderAge) {
    this.senderAge = senderAge;
  }

  public String getBeneficiaryFullName() {
    return beneficiaryFullName;
  }

  public void setBeneficiaryFullName(String beneficiaryFullName) {
    this.beneficiaryFullName = beneficiaryFullName;
  }

  public Integer getBeneficiaryAge() {
    return beneficiaryAge;
  }

  public void setBeneficiaryAge(Integer beneficiaryAge) {
    this.beneficiaryAge = beneficiaryAge;
  }

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
    Transaction that = (Transaction) o;
    return Objects.equals(getMtn(), that.getMtn())
        && Objects.equals(getAmount(), that.getAmount())
        && Objects.equals(getSenderFullName(), that.getSenderFullName())
        && Objects.equals(getSenderAge(), that.getSenderAge())
        && Objects.equals(getBeneficiaryFullName(), that.getBeneficiaryFullName())
        && Objects.equals(getBeneficiaryAge(), that.getBeneficiaryAge());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getMtn(),
        getAmount(),
        getSenderFullName(),
        getSenderAge(),
        getBeneficiaryFullName(),
        getBeneficiaryAge());
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("mtn", mtn)
        .append("amount", amount)
        .append("sendFullName", senderFullName)
        .append("senderAge", senderAge)
        .append("beneficiaryFullName", beneficiaryFullName)
        .append("beneficiaryAge", beneficiaryAge)
        .append("issueId", issueId)
        .append("issueSolved", issueSolved)
        .append("issueMessage", issueMessage)
        .toString();
  }
}
