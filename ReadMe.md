# Right now, for the completion of the payment process we need to perform 2 steps:

    1. Create Order : An order represents a payment between 2 or more parties, in our case represents the payment
    between the customer and the airline. This step starts when the customer places order and ends when the customer is 
    redirected back to the checkout page after initiating the transaction on PayPal.

    2. Capture Order: This step is used to approve the order which is placed at step 1