# Right now, for the completion of the payment process we need to perform 2 steps:

    1. Create Order : An order represents a payment between 2 or more parties, in our case represents the payment
    between the customer and the airline. This step starts when the customer places order and ends when the customer is 
    redirected back to the checkout page after initiating the transaction on PayPal.

    !! 1 -> publish payment created by you in PayPal (returns the url that redirects you for processing the payment).

    2. Capture Order: Confirm/execute the payment by calling back PayPal.




    !!!!
    ### Odata ce am creat paymentul -> dupa ce il si capturam (al doilea ai), de acolo putem sa extragem informatiile 
    despre cel care a facut plata (andrei.rosu@mail.com) DAR nu va arata si info pentru payee (tarom).


    ### Daca mai intai creez order ul, dupa il capturez, si dupa fac acel get de order, atunci obiectul Order va contine
        atat informatiile despre Payer cat si despre Payee!