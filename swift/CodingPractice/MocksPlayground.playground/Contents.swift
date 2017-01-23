//: Playground - noun: a place where people can play

import Cocoa

//: Mocks Playground
//import UIKit

struct User {
}

class UIUserNotificationSettings {}

struct PushNotificationController {
    let registrar: PushNotificationRegistrar
    init(registrar: PushNotificationRegistrar) {
        self.registrar = registrar
    }
    
    var user: User? {
        didSet {
            if let _ = user {
                registrar.registerUserNotificationSettings(UIUserNotificationSettings())
            }
        }
    }
}

protocol PushNotificationRegistrar {
    func registerUserNotificationSettings(_ notificationSettings: UIUserNotificationSettings)
}

class UIApplication {}

extension UIApplication: PushNotificationRegistrar {
    internal func registerUserNotificationSettings(_ notificationSettings: UIUserNotificationSettings) {
        
    }
 }

class FauxRegistrar: PushNotificationRegistrar {
    var registered = false
    func registerUserNotificationSettings(_ notificationSettings: UIUserNotificationSettings) {
        registered = true
    }
}

var registrar = FauxRegistrar()
var controller = PushNotificationController(registrar: registrar)
controller.user = User()

registrar.registered