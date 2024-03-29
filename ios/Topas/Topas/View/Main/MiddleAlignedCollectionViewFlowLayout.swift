//
//  MiddleAlignedCollectionViewFlowLayout.swift
//  Topas
//
//  Created by 김경호 on 2022/11/24.
//

import UIKit
import SnapKit

class MiddleAlignedCollectionViewFlowLayout: UICollectionViewFlowLayout {
    override func layoutAttributesForElements(in rect: CGRect) -> [UICollectionViewLayoutAttributes]? {
        print("Layout-------------------------------")
        
        guard let atts = super.layoutAttributesForElements(in: rect) else {
            return []
        }
        
        var selfHeight = 0
        // 값 체크
        for i in atts {
            print(i.frame, i.indexPath.row)
            selfHeight = max(Int(i.frame.maxY), selfHeight)
        }
        print(selfHeight)
        for (idx, val) in atts.enumerated(){
            // 제일 처음 cell이면 패스
            if idx == 0 {
                continue
            }
            
            
            // 옆에 cell의 위치와 최소한의 값을 옆으로
            if atts[idx-1].frame.maxY == atts[idx].frame.maxY {val.frame.origin.x = (atts[idx - 1].frame.maxX + minimumLineSpacing)}
        }
        
        return atts
    }
}

class LeftAlignedCollectionViewFlowLayout: UICollectionViewFlowLayout {
  override func layoutAttributesForElements(in rect: CGRect) -> [UICollectionViewLayoutAttributes]? {
    // 재정의 오버라이드 메소드 임으로 리턴값으로 layout 속성값들을 받습니다.
    let attributes = super.layoutAttributesForElements(in: rect)
    
    // contentView의 left 여백
    var leftMargin = sectionInset.left
    var maxY: CGFloat = -1.0 // cell라인의 y값의 디폴트값
    attributes?.forEach { layoutAttribute in
        // cell일경우
      if layoutAttribute.representedElementCategory == .cell {
        // 한 cell의 y 값이 이전 cell들이 들어갔더 line의 y값보다 크다면
        // 디폴트값을 -1을 줬기 때문에 처음은 무조건 발동, x좌표 left에서 시작
        if layoutAttribute.frame.origin.y >= maxY {
          leftMargin = sectionInset.left
        }
        // cell의 x좌표에 leftMargin값 적용해주고
        layoutAttribute.frame.origin.x = leftMargin
        // cell의 다음값만큼 cellWidth + minimumInteritemSpacing + 해줌
        leftMargin += layoutAttribute.frame.width + minimumInteritemSpacing
        // cell의 위치값과 maxY변수값 중 최대값 넣어줌(라인 y축값 업데이트)
        maxY = max(layoutAttribute.frame.maxY, maxY)
      }
    }
    return attributes
  }
}
