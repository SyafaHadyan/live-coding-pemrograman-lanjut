@startuml
skinparam classAttributeIconSize 0
class Hotel{
+ {static} main(args : String[]) : void
}
class Guest{
- name : String
- paymentMethod : String
- guestType : String
- nightCount : int
- point : int
- costPerNight : double
- extras : double
- finalCostBeforeDiscount : double
- finalCostAfterDiscount : double
- finalCost : double
+ displayInfo() : void
+ setName(name : String) : void
+ setPaymentMethod(paymentMethod : String) : void
+ setGuestType(guestType : String) : void
+ setNightCount(nightCount : int) : void
+ setPoint(point : int) : void
+ setExtras(extras : double) : void
+ setFinalCostBeforeDiscount(finalCostBeforeDiscount : double) : void
+ setFinalCostAfterDiscount(finalCostAfterDiscount : double) : void
+ getCostPerNight() : double
}
class Regular{
- POINT_PER_NIGHT : int
- DISCOUNT_PERCENTAGE : double
- finalCostBeforeDiscount : double
- finalCostAfterDiscount : double
+ setInfo(name : String, guestType : String, paymentMethod : String, nightCount : int) : void
}
Regular --|> Guest
class Member{
- POINT_PER_NIGHT : int
- DISCOUNT_PERCENTAGE : double
- finalCostBeforeDiscount : double
- finalCostAfterDiscount : double
+ setInfo(name : String, guestType : String, paymentMethod : String, nightCount : int) : void
}
Member --|> Guest
class VIP{
- POINT_PER_NIGHT : int
- DISCOUNT_PERCENTAGE : double
- finalCostBeforeDiscount : double
- finalCostAfterDiscount : double
+ setInfo(name : String, guestType : String, paymentMethod : String, nightCount : int) : void
}
VIP --|> Guest
@enduml
