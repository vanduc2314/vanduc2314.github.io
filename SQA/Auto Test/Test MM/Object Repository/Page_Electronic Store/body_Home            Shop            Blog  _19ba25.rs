<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>body_Home            Shop            Blog  _19ba25</name>
   <tag></tag>
   <elementGuidId>39b597cf-7c78-46e1-ba2e-20c56bbdd461</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>//body</value>
      </entry>
      <entry>
         <key>CSS</key>
         <value>body</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>true</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>body</value>
      <webElementGuid>4dce1dba-d308-40b8-9bf7-4edf9b8e085d</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>

            




    
        
    

    
        
            Home
            Shop
            Blog
            Cart
        
    
    MENU
        
            Home
            Shop
            Blog
            Cart
        
    
    
        
        
        
        
    
    
        
            Free Shipping for all Order
        
    





    
        
            
                
                    
                
            
            
                
                    
                        
                            
                                Home
                                
                                    
                                        Report
                                    
                                    
                                        Product Statistic
                                        Employee Statistic
                                    
                                

                            
                          
                         
                        
                    

                
            
            
                
                    
                    
                        
                            
                                Pham Van Anh
                                manager
                            
                            
                        
                    
                
            
        
        
            
        
    


    
    


            
                Statistic Product by Revenue
                   
            

                Start Date:
                

                End Date:
                


                
                    Statistic
                
            
                
                    
                   

            

                
                     Sort
                    
                        Quantity
                        Low to High 
                        High to Low

                    QuantityQuantityLow to High High to Low

                    
                        Revenue
                        Low to High 
                        High to Low

                    RevenueRevenueLow to High High to Low
                

            
            
                
                    
                        
                            
                                SKU
                                Product's name
                                Sold Quantity
                                Amount
                            
                        
                        
                        

                    
                



            
            
            
            
            

            
            
            
            
            
            
            
            
            
                $(document).ready(function () {
                    $(document).on(&quot;click&quot;, &quot;.sub-date&quot;, function (e) {
                        var sdate = $(&quot;#sdate&quot;).val();
                        console.log(sdate);
                        var edate = $(&quot;#edate&quot;).val();
                        $.ajax({
                            url: &quot;Productstat&quot;,
                            method: &quot;post&quot;,
                            data: {sdate: sdate,
                                edate: edate
                            },
                            success: function (response) {
                                $(&quot;#content&quot;).html(response);
                            }
                        });

                    }
                    );
                }
                );
            
        

    
    


/html[1]/body[1]</value>
      <webElementGuid>3c610eae-b525-41fe-97b8-78f8bd74e8f4</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>/html[1]/body[1]</value>
      <webElementGuid>013e7076-ff93-4e96-8539-11d19a443ec9</webElementGuid>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//body</value>
      <webElementGuid>d03ac215-7556-4ffc-adef-18b65a55f31b</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:customAttributes</name>
      <type>Main</type>
      <value>//body[(text() = concat(&quot;

            




    
        
    

    
        
            Home
            Shop
            Blog
            Cart
        
    
    MENU
        
            Home
            Shop
            Blog
            Cart
        
    
    
        
        
        
        
    
    
        
            Free Shipping for all Order
        
    





    
        
            
                
                    
                
            
            
                
                    
                        
                            
                                Home
                                
                                    
                                        Report
                                    
                                    
                                        Product Statistic
                                        Employee Statistic
                                    
                                

                            
                          
                         
                        
                    

                
            
            
                
                    
                    
                        
                            
                                Pham Van Anh
                                manager
                            
                            
                        
                    
                
            
        
        
            
        
    


    
    


            
                Statistic Product by Revenue
                   
            

                Start Date:
                

                End Date:
                


                
                    Statistic
                
            
                
                    
                   

            

                
                     Sort
                    
                        Quantity
                        Low to High 
                        High to Low

                    QuantityQuantityLow to High High to Low

                    
                        Revenue
                        Low to High 
                        High to Low

                    RevenueRevenueLow to High High to Low
                

            
            
                
                    
                        
                            
                                SKU
                                Product&quot; , &quot;'&quot; , &quot;s name
                                Sold Quantity
                                Amount
                            
                        
                        
                        

                    
                



            
            
            
            
            

            
            
            
            
            
            
            
            
            
                $(document).ready(function () {
                    $(document).on(&quot;click&quot;, &quot;.sub-date&quot;, function (e) {
                        var sdate = $(&quot;#sdate&quot;).val();
                        console.log(sdate);
                        var edate = $(&quot;#edate&quot;).val();
                        $.ajax({
                            url: &quot;Productstat&quot;,
                            method: &quot;post&quot;,
                            data: {sdate: sdate,
                                edate: edate
                            },
                            success: function (response) {
                                $(&quot;#content&quot;).html(response);
                            }
                        });

                    }
                    );
                }
                );
            
        

    
    


/html[1]/body[1]&quot;) or . = concat(&quot;

            




    
        
    

    
        
            Home
            Shop
            Blog
            Cart
        
    
    MENU
        
            Home
            Shop
            Blog
            Cart
        
    
    
        
        
        
        
    
    
        
            Free Shipping for all Order
        
    





    
        
            
                
                    
                
            
            
                
                    
                        
                            
                                Home
                                
                                    
                                        Report
                                    
                                    
                                        Product Statistic
                                        Employee Statistic
                                    
                                

                            
                          
                         
                        
                    

                
            
            
                
                    
                    
                        
                            
                                Pham Van Anh
                                manager
                            
                            
                        
                    
                
            
        
        
            
        
    


    
    


            
                Statistic Product by Revenue
                   
            

                Start Date:
                

                End Date:
                


                
                    Statistic
                
            
                
                    
                   

            

                
                     Sort
                    
                        Quantity
                        Low to High 
                        High to Low

                    QuantityQuantityLow to High High to Low

                    
                        Revenue
                        Low to High 
                        High to Low

                    RevenueRevenueLow to High High to Low
                

            
            
                
                    
                        
                            
                                SKU
                                Product&quot; , &quot;'&quot; , &quot;s name
                                Sold Quantity
                                Amount
                            
                        
                        
                        

                    
                



            
            
            
            
            

            
            
            
            
            
            
            
            
            
                $(document).ready(function () {
                    $(document).on(&quot;click&quot;, &quot;.sub-date&quot;, function (e) {
                        var sdate = $(&quot;#sdate&quot;).val();
                        console.log(sdate);
                        var edate = $(&quot;#edate&quot;).val();
                        $.ajax({
                            url: &quot;Productstat&quot;,
                            method: &quot;post&quot;,
                            data: {sdate: sdate,
                                edate: edate
                            },
                            success: function (response) {
                                $(&quot;#content&quot;).html(response);
                            }
                        });

                    }
                    );
                }
                );
            
        

    
    


/html[1]/body[1]&quot;))]</value>
      <webElementGuid>c14ec451-5e7d-472f-a86e-03f7360e086d</webElementGuid>
   </webElementXpaths>
</WebElementEntity>
