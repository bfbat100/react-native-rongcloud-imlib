//
//  RCCustomMessageContent.h
//  RCIMLib
//
//  Created by MAC on 2019/10/23.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <RongIMLib/RongIMLib.h>
NS_ASSUME_NONNULL_BEGIN

@interface RCCustomMessageContent : RCMessageContent

@property (nonatomic,strong) NSString *content;
@property (nonatomic,strong) NSString *extra;

/*!
 初始化文本消息
 
 @param content 文本消息的内容
 @return        文本消息对象
 */
+ (instancetype)initWithMessageWithContent:(NSString *)content extra:(NSString *)extra;
- (instancetype)initWithCustomMessage:(NSString *)content extra:(NSString *)extra;

@end

NS_ASSUME_NONNULL_END
