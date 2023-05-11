package com.mdy.sample.unit

import com.mdy.sample.annotation.UnitTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@UnitTest
class ResponseDtoParsingTest {

    @Test
    @DisplayName("Response dto parsing test")
    fun a() {
        val jsonString = this.getTestJsonString()
        println("# jsonString: $jsonString")

        println("=========================================")

        val responseDto = JsonTestDto(json = jsonString)
        val pgOrderId = responseDto.getPgOrderId()
        val pgCaptureId = responseDto.getPgCaptureId()
        println("# pgOrderId: $pgOrderId")
        println("# pgCaptureId: $pgCaptureId")
    }

    private fun getTestJsonString(): String {
        return """
            {
              "id": "7E082348ES361252J",
              "status": "COMPLETED",
              "payment_source": {
                "paypal": {
                  "email_address": "sb-wiojd18928720@example.com",
                  "account_id": "3YH3UWPTWKH3G",
                  "name": {
                    "given_name": "John",
                    "surname": "Doe"
                  },
                  "address": {
                    "country_code": "US"
                  }
                }
              },
              "purchase_units": [
                {
                  "reference_id": "default",
                  "shipping": {
                    "name": {
                      "full_name": "John Doe"
                    },
                    "address": {
                      "address_line_1": "1 Main St",
                      "admin_area_2": "San Jose",
                      "admin_area_1": "CA",
                      "postal_code": "92536",
                      "country_code": "US"
                    }
                  },
                  "payments": {
                    "captures": [
                      {
                        "id": "9E521379HW0644749",
                        "status": "COMPLETED",
                        "amount": {
                          "currency_code": "USD",
                          "value": "100.00"
                        },
                        "final_capture": true,
                        "seller_protection": {
                          "status": "ELIGIBLE",
                          "dispute_categories": [
                            "ITEM_NOT_RECEIVED",
                            "UNAUTHORIZED_TRANSACTION"
                          ]
                        },
                        "seller_receivable_breakdown": {
                          "gross_amount": {
                            "currency_code": "USD",
                            "value": "100.00"
                          },
                          "paypal_fee": {
                            "currency_code": "USD",
                            "value": "3.98"
                          },
                          "net_amount": {
                            "currency_code": "USD",
                            "value": "96.02"
                          }
                        },
                        "links": [
                          {
                            "href": "https://api.sandbox.paypal.com/v2/payments/captures/9E521379HW0644749",
                            "rel": "self",
                            "method": "GET"
                          },
                          {
                            "href": "https://api.sandbox.paypal.com/v2/payments/captures/9E521379HW0644749/refund",
                            "rel": "refund",
                            "method": "POST"
                          },
                          {
                            "href": "https://api.sandbox.paypal.com/v2/checkout/orders/4C088348BL398353H",
                            "rel": "up",
                            "method": "GET"
                          }
                        ],
                        "create_time": "2022-11-01T10:28:47Z",
                        "update_time": "2022-11-01T10:28:47Z"
                      }
                    ]
                  }
                }
              ],
              "payer": {
                "name": {
                  "given_name": "John",
                  "surname": "Doe"
                },
                "email_address": "sb-wiojd18928720@example.com",
                "payer_id": "7EH2ISDOMWE5E",
                "address": {
                  "country_code": "US"
                }
              },
              "links": [
                {
                  "href": "https://api.sandbox.paypal.com/v2/checkout/orders/7E082348ES361252J",
                  "rel": "self",
                  "method": "GET"
                }
              ]
            }
        """.trimIndent()
    }
}