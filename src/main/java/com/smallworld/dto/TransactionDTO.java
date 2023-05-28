package com.smallworld.dto;

import com.smallworld.entity.IssueDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/***
 * Created Class to map Transactions for DTO in-case
 * the transaction is duplicated due to issue it will add-up in the
 * Set<IssueDTO> issues
 */
public class TransactionDTO {
  private Integer mtn;
  private Double amount;
  private String senderFullName;
  private Integer senderAge;
  private String beneficiaryFullName;
  private Integer beneficiaryAge;
  private List<IssueDTO> issues = new ArrayList<>();

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

  public void setSenderFullName(String senderFullName) {
    this.senderFullName = senderFullName;
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

  public List<IssueDTO> getIssues() {
    return issues;
  }

  public void setIssues(List<IssueDTO> issues) {
    this.issues = issues;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionDTO that = (TransactionDTO) o;
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
            .append("issues", issues)
            .toString();
  }
}
