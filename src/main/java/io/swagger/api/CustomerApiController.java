package io.swagger.api;

import io.swagger.model.Balance;
import io.swagger.model.Customer;
import io.swagger.model.Customer.StatusEnum;
import io.swagger.model.ModelApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import io.swagger.model.Transaction;
import io.swagger.model.Transaction.StatusEnum1;
import io.swagger.repos.CustomerRepo;
import io.swagger.repos.TransactionRepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-08-27T06:08:27.613Z[GMT]")
@Controller
public class CustomerApiController implements CustomerApi {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	TransactionRepo trxRepo;

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addPet(@ApiParam(value = "Customer object that needs to be added to the bank" ,required=true )  @Valid @RequestBody Customer body
) {
        customerRepo.save(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteCustomer(@ApiParam(value = "customer id to delete",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey
) {
        String accept = request.getHeader("Accept");
        customerRepo.delete(customerId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteOrder(@Min(1L)@ApiParam(value = "ID of the order that needs to be deleted",required=true, allowableValues="") @PathVariable("accountId") Long accountId
) {
        String accept = request.getHeader("Accept");
        
        customerRepo.delete(customerRepo.findById(accountId));
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Customer>> findCustomersByStatus(@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "active, hold, inactive, pending") @Valid @RequestParam(value = "status", required = true) List<String> status
) {
    	List<Customer> outList = new ArrayList<>();
    	
        String accept = request.getHeader("Accept");
        if (accept != null) {
        	System.out.println("----> inside iffffff");
//            List<Customer> listww = new ArrayList<>();
//			Customer c = new Customer();
//			c.setName("reslut name");
//			listww.add(c);
//			System.out.println("------> url status list : " + status);
//			System.out.println("------> url status list : " + listww);
//			//return new ResponseEntity<List<Customer>>(objectMapper.readValue("[ {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n}, {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
//			return new ResponseEntity<List<Customer>>(listww, HttpStatus.OK);
        	
        	
            for(String statusItem : status)
            {
            	StatusEnum statusEnum = StatusEnum.valueOf(statusItem);
            	outList.addAll(customerRepo.findByStatus(statusEnum));
            }
            
            System.out.println("-----> customer status " + outList);
            return new ResponseEntity<List<Customer>>(outList, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<List<Customer>>(outList, HttpStatus.ACCEPTED);
    	
//        String[] statusArr = new String[status.size()];
//    	for()
        
    }

    public ResponseEntity<List<Customer>> findPetsByAccount(@NotNull @ApiParam(value = "Tags to filter by", required = true) @Valid @RequestParam(value = "tags", required = true) List<String> tags
) {
    	List<Customer> outList = new ArrayList<>();
    	
        String accept = request.getHeader("Accept");
        if (accept != null) {
//            try {
//                return new ResponseEntity<List<Customer>>(objectMapper.readValue("[ {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n}, {\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
        	for(String accountType : tags)
            {
            	outList.addAll(customerRepo.findByAccount(accountType));
            }
        	System.out.println("-----> customer status " + outList);
            return new ResponseEntity<List<Customer>>(outList, HttpStatus.OK);
        }

        return new ResponseEntity<List<Customer>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Customer> getCustomerById(@ApiParam(value = "ID of pet to return",required=true) @PathVariable("customerId") Long customerId
) {
        String accept = request.getHeader("Accept");
        if (accept != null) {
//            try {
//                return new ResponseEntity<Customer>(objectMapper.readValue("{\n  \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ],\n  \"name\" : \"doggie\",\n  \"id\" : 0,\n  \"category\" : {\n    \"name\" : \"name\",\n    \"id\" : 6\n  },\n  \"tags\" : [ {\n    \"name\" : \"name\",\n    \"id\" : 1\n  }, {\n    \"name\" : \"name\",\n    \"id\" : 1\n  } ],\n  \"status\" : \"available\"\n}", Customer.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
        	Customer cust = customerRepo.findById(customerId);
        	System.out.println("-----> got customer " + cust);
        	return new ResponseEntity<Customer>(HttpStatus.OK);
        }

        return new ResponseEntity<Customer>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@Min(1L) @Max(10L) @ApiParam(value = "ID of pet that needs to be fetched",required=true, allowableValues="") @PathVariable("accountId") Long accountId
) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"accountId\" : 6,\n  \"quantity\" : 1,\n  \"id\" : 0,\n  \"transactionDate\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"complete\" : false,\n  \"status\" : \"credit\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
    	
    	List<Transaction> allTrxs = trxRepo.findByAccountId(accountId);

        return new ResponseEntity<List<Transaction>>(allTrxs, HttpStatus.OK);
    }

    public ResponseEntity<Balance> placeOrder(@ApiParam(value = "ID of customer to balance",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "ID of account to get balance",required=true) @PathVariable("accountId") Long accountId
,@ApiParam(value = "order placed for purchasing the pet" ,required=true )  @Valid @RequestBody Balance body
) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<Balance>(objectMapper.readValue("{\n  \"accountId\" : 0,\n  \"quantity\" : 6,\n  \"lastTransactionDate\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"asOfDate\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"complete\" : false\n}", Balance.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<Balance>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
    	
    	Customer c = customerRepo.findById(customerId);
    	if( c != null)
    	{
    		c.setBalance(c.getBalance() + new Long(body.getQuantity()));
    		System.out.println("-----> balance to upd is " + c.getBalance());
    		customerRepo.updateCustomerBalance(c.getBalance(), c.getId());
    		body.setAccountId(customerId);
    		body.setAsOfDate(body.getAsOfDate());
    		body.setComplete(true);
    		body.setLastTransactionDate(body.getLastTransactionDate());
    		body.setQuantity(body.getQuantity());
    		return new ResponseEntity<Balance>(body, HttpStatus.OK);
    	}

        return new ResponseEntity<Balance>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> transfer(@ApiParam(value = "ID of customer to balance",required=true) @PathVariable("customerId") Long customerId
,@ApiParam(value = "ID of account to get balance",required=true) @PathVariable("accountId") Long accountId
,@ApiParam(value = "request transfer between accounts" ,required=true )  @Valid @RequestBody Transaction body
) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"accountId\" : 6,\n  \"quantity\" : 1,\n  \"id\" : 0,\n  \"transactionDate\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"complete\" : false,\n  \"status\" : \"credit\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
    	
    	Customer cfrom = customerRepo.findById(customerId);
    	if(cfrom != null)
    	{
    		Customer cTo = customerRepo.findById(accountId);
    		if(body.getStatus().toString().equals("credit"))
    		{
    			cTo.setBalance(cTo.getBalance() + body.getQuantity());
    			StatusEnum1 statusEnum = StatusEnum1.valueOf("CREDIT");
    			body.setStatus(statusEnum);
    			
    			Transaction t1from = new Transaction();
    			t1from.setAccountId(cfrom.getId());
    			t1from.setComplete(Boolean.TRUE);
    			t1from.setQuantity(body.getQuantity());
    			StatusEnum1 statusEnumFrom = StatusEnum1.valueOf("DEBIT");
    			t1from.setStatus(StatusEnum1.DEBIT);
    			t1from.setTransactionDate(body.getTransactionDate());
    			trxRepo.save(t1from);
    			
    			Transaction t1to = new Transaction();
    			t1to.setAccountId(cTo.getId());
    			t1to.setComplete(Boolean.TRUE);
    			t1to.setQuantity(body.getQuantity());
    			StatusEnum1 statusEnumTo = StatusEnum1.valueOf("CREDIT");
    			t1to.setStatus(StatusEnum1.CREDIT);
    			t1to.setTransactionDate(body.getTransactionDate());
    			trxRepo.save(t1to);
    		}
    		else
    		{
    			cTo.setBalance(cTo.getBalance() - body.getQuantity());
    			StatusEnum1 statusEnum = StatusEnum1.valueOf("DEBIT");
    			body.setStatus(statusEnum);
    		}
    		customerRepo.updateCustomerBalance(cTo.getBalance(), cTo.getId());
    		body.setId(cfrom.getId());
    		body.setAccountId(cTo.getId());
    		body.setQuantity(body.getQuantity());
    		body.setTransactionDate(body.getTransactionDate());
    		body.setComplete(true);
    		return new ResponseEntity<Transaction>(body, HttpStatus.OK);
    	}

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    //PUT /customer ---update an existing customer
    public ResponseEntity<Void> updateCusotmer(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Customer custbody
) {
    	long custid = custbody.getId();
        Customer customer = customerRepo.findById(custid);
   
        customerRepo.updateCustomer(custbody.getName(), custbody.getCity(), custid);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateCustomerWithForm(@ApiParam(value = "ID of customer that needs to be updated",required=true) @PathVariable("customerId") Long customerId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModelApiResponse> uploadFile(@ApiParam(value = "ID of customer to update",required=true) @PathVariable("customerId") Long customerId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModelApiResponse>(objectMapper.readValue("{\n  \"code\" : 0,\n  \"type\" : \"type\",\n  \"message\" : \"message\"\n}", ModelApiResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModelApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModelApiResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
