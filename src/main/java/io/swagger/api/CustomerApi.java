/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.21).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Balance;
import io.swagger.model.Customer;
import io.swagger.model.ModelApiResponse;
import org.springframework.core.io.Resource;
import io.swagger.model.Transaction;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-08-27T06:08:27.613Z[GMT]")
@Api(value = "customer", description = "the customer API")
public interface CustomerApi {

    @ApiOperation(value = "Add a new customer to bank", nickname = "addPet", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/customer",
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addPet(@ApiParam(value = "Customer object that needs to be added to the bank" ,required=true )  @Valid @RequestBody Customer body
);


    @ApiOperation(value = "Deletes a customer", nickname = "deleteCustomer", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found") })
    @RequestMapping(value = "/customer/{customerId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCustomer(@ApiParam(value = "customer id to delete",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey
);


    @ApiOperation(value = "Delete account  by ID", nickname = "deleteOrder", notes = "For valid response try integer IDs with positive integer value.         Negative or non-integer values will generate API errors", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Order not found") })
    @RequestMapping(value = "/customer/account/{accountId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteOrder(@Min(1L)@ApiParam(value = "ID of the order that needs to be deleted",required=true, allowableValues="") @PathVariable("accountId") Long accountId
);


    @ApiOperation(value = "Finds customer by status", nickname = "findCustomersByStatus", notes = "Multiple status values can be provided with comma separated strings", response = Customer.class, responseContainer = "List", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Customer.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value") })
    @RequestMapping(value = "/customer/findByStatus",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Customer>> findCustomersByStatus(@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "active, hold, inactive, pending") @Valid @RequestParam(value = "status", required = true) List<String> status
);


    @ApiOperation(value = "Finds customer by account", nickname = "findPetsByAccount", notes = "Muliple accounts can be provided with comma separated strings. Use         account1, account2, account3 for testing.", response = Customer.class, responseContainer = "List", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Customer.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/customer/accounts",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Customer>> findPetsByAccount(@NotNull @ApiParam(value = "Tags to filter by", required = true) @Valid @RequestParam(value = "tags", required = true) List<String> tags
);


    @ApiOperation(value = "Find customer by ID", nickname = "getCustomerById", notes = "Returns a single customer", response = Customer.class, authorizations = {
        @Authorization(value = "api_key")    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Customer.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Pet not found") })
    @RequestMapping(value = "/customer/{customerId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Customer> getCustomerById(@ApiParam(value = "ID of pet to return",required=true) @PathVariable("customerId") Long customerId
);


    @ApiOperation(value = "retrive transaction by account Id", nickname = "getTransactionsByAccountId", notes = "For valid response try integer IDs with value >= 1 and <= 10.         Other values will generated exceptions", response = Transaction.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Transaction.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Order not found") })
    @RequestMapping(value = "/customer/account/{accountId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Transaction> getTransactionsByAccountId(@Min(1L) @Max(10L) @ApiParam(value = "ID of pet that needs to be fetched",required=true, allowableValues="") @PathVariable("accountId") Long accountId
);


    @ApiOperation(value = "Place an order for a pet", nickname = "placeOrder", notes = "", response = Balance.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Balance.class),
        @ApiResponse(code = 400, message = "Invalid Order") })
    @RequestMapping(value = "/customer/{customerId}/account/{accountId}/balance",
        produces = { "application/xml", "application/json" }, 
        consumes = { "*/*" },
        method = RequestMethod.POST)
    ResponseEntity<Balance> placeOrder(@ApiParam(value = "ID of customer to balance",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "ID of account to get balance",required=true) @PathVariable("accountId") Long accountId
,@ApiParam(value = "order placed for purchasing the pet" ,required=true )  @Valid @RequestBody Balance body
);


    @ApiOperation(value = "Place an order for a pet", nickname = "transfer", notes = "", response = Transaction.class, tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Transaction.class),
        @ApiResponse(code = 400, message = "Invalid Order") })
    @RequestMapping(value = "/customer/{customerId}/account/{accountId}/transfer",
        produces = { "application/xml", "application/json" }, 
        consumes = { "*/*" },
        method = RequestMethod.POST)
    ResponseEntity<Transaction> transfer(@ApiParam(value = "ID of customer to balance",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "ID of account to get balance",required=true) @PathVariable("accountId") Long accountId
,@ApiParam(value = "request transfer between accounts" ,required=true )  @Valid @RequestBody Transaction body
);


    @ApiOperation(value = "Update an existing customer", nickname = "updateCusotmer", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Customer not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/customer",
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateCusotmer(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Customer body
);


    @ApiOperation(value = "Updates a customer in the bank with form data", nickname = "updateCustomerWithForm", notes = "", authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/customer/{customerId}",
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> updateCustomerWithForm(@ApiParam(value = "ID of customer that needs to be updated",required=true) @PathVariable("customerId") Long customerId
);


    @ApiOperation(value = "uploads an image", nickname = "uploadFile", notes = "", response = ModelApiResponse.class, authorizations = {
        @Authorization(value = "petstore_auth", scopes = { 
            @AuthorizationScope(scope = "write:customers", description = "modify customers in your account"),
            @AuthorizationScope(scope = "read:customers", description = "read your accounts")
            })    }, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
    @RequestMapping(value = "/customer/{customerId}/uploadImage",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<ModelApiResponse> uploadFile(@ApiParam(value = "ID of customer to update",required=true) @PathVariable("customerId") Long customerId
);

}

