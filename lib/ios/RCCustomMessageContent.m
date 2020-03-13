//
//  RCCustomMessageContent.m
//  RCIMLib
//
//  Created by MAC on 2019/10/23.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RCCustomMessageContent.h"

@implementation RCCustomMessageContent

+ (instancetype)initWithMessageWithContent:(NSString *)content extra:(nonnull NSString *)extra{
   
    return [[self alloc]initWithCustomMessage:content extra:extra];
}
- (instancetype)initWithCustomMessage:(NSString *)content extra:(nonnull NSString *)extra{
    
    if (self = [super init]) {
        self.content = content;
        self.extra = extra;
    }
    return self;
}

/*!
 将消息内容序列化，编码成为可传输的json数据
 
 @discussion 消息内容通过此方法，将消息中的所有数据，编码成为json数据，返回的json数据将用于网络传输。
 */
- (NSData *)encode{
    NSDictionary *dict = @{@"content":self.content, @"extra": self.extra};
    NSError *error;
    NSData *data = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:&error];
    return data;
}

/*!
 将json数据的内容反序列化，解码生成可用的消息内容
 
 @param data    消息中的原始json数据
 
 @discussion 网络传输的json数据，会通过此方法解码，获取消息内容中的所有数据，生成有效的消息内容。
 */
- (void)decodeWithData:(NSData *)data{
    NSError *error;
    NSDictionary *jsonDict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&error];
    self.content = jsonDict[@"content"];
    self.extra = jsonDict[@"extra"];
}

/*!
 返回消息的类型名
 
 @return 消息的类型名
 
 @discussion 您定义的消息类型名，需要在各个平台上保持一致，以保证消息互通。
 
 @warning 请勿使用@"RC:"开头的类型名，以免和SDK默认的消息名称冲突
 */
+ (NSString *)getObjectName{
    return @"system:noPush";
}



@end

