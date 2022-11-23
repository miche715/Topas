//
//  team,.swift
//  Topas
//
//  Created by 김경호 on 2022/08/30.
//

import UIKit

class Team : UIViewController{
    
    let vc = TeamView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view = vc
    }
}
