package de.ama.framework.gui.icons {
import de.ama.framework.gui.frames.ApplicationPanel;

public class IconStore {

  [Embed(source="../icons/TabCloseButtonGray.gif")]
  public static const TAB_CLOSE_BUTTON_GRAY:Class;

  [Embed(source="../icons/TabCloseButtonRed.png")]
  public static const TAB_CLOSE_BUTTON_RED:Class;

  [Embed(source="../icons/disk.png")]
  public static const SAVE:Class;

  [Embed(source="../icons/folder.png")]
  public static const FOLDER:Class;

  [Embed(source="../icons/clock.png")]
  public static const CLOCK:Class;

  [Embed(source="../icons/group.png")]
  public static const GROUP:Class;

  [Embed(source="../icons/cut.png")]
  public static const CUT:Class;

  [Embed(source="../icons/car.png")]
  public static const CAR:Class;

  [Embed(source="../icons/calculator.png")]
  public static const CALCULATOR:Class;

  [Embed(source="../icons/bin.png")]
  public static const BIN:Class;

  [Embed(source="../icons/zoom.png")]
  public static const SEARCH:Class;

  [Embed(source="../icons/wrench.png")]
  public static const WRENCH:Class;

  [Embed(source="../icons/arrow_right.png")]
  public static const ARROW_LEFT:Class;

  [Embed(source="../icons/arrow_right.png")]
  public static const ARROW_RIGHT:Class;

  [Embed(source="../icons/accept.png")]
  public static const ACCEPT:Class;

  [Embed(source="../icons/information.png")]
  public static const INFORMATION:Class;

  [Embed(source="../icons/error.png")]
  public static const ERROR:Class;

  [Embed(source="../icons/page_white.png")]
  public static const NEW:Class;

  [Embed(source="../icons/page_white_copy.png")]
  public static const COPY:Class;

  [Embed(source="../icons/user.png")]
  public static const USER:Class;

  [Embed(source="../icons/user_gray.png")]
  public static const USER_GRAY:Class;

  [Embed(source="../icons/user_green.png")]
  public static const USER_GREEN:Class;

  [Embed(source="../icons/user_red.png")]
  public static const USER_RED:Class;

  [Embed(source="../icons/user_orange.png")]
  public static const USER_ORANGE:Class;

  [Embed(source="../icons/cancel.png")]
  public static const CANCEL:Class;

  [Embed(source="../icons/application_form_edit.png")]
  public static const EDIT:Class;

  [Embed(source="../icons/magnifier.png")]
  public static const LOOKUP:Class;

  [Embed(source="../icons/exclamation.png")]
  public static const ATENTION:Class;

  [Embed(source="../icons/email_go.png")]
  public static const EMAIL:Class;

  [Embed(source="../icons/application_form.png")]
  public static const FORM:Class;

  [Embed(source="../icons/lock.png")]
  public static const LOCK:Class;

  [Embed(source="../icons/table.png")]
  public static const TABLE:Class;

  [Embed(source="../icons/basket.png")]
  public static const PACK:Class;

  [Embed(source="../icons/page_white_text.png")]
  public static const DOCK:Class;

  [Embed(source="../icons/lorry.png")]
  public static const LORRY:Class;

  [Embed(source="../icons/printer.png")]
  public static const PRINT:Class;

  public static function getIcon(iconName:String):Class{
      if (iconName == null) {
          return CANCEL;
      } else {
          switch (iconName) {
              case "user":          return  USER;
              case "group":         return GROUP;
              case "user.orange":   return USER_ORANGE;
              case "user.red":      return USER_RED;
              case "user.green":    return USER_GREEN;
              case "user.gray":     return USER_GRAY;
              case "cancel":        return CANCEL;
              case "copy":          return COPY;
              case "error":         return ERROR;
              case "accept":        return ACCEPT;
              case "cut":           return CUT;
              case "bin":           return BIN;
              case "calculator":    return CALCULATOR;
              case "clock":         return CLOCK;
              case "save":          return SAVE;
              case "info":          return INFORMATION;
              case "car":           return CAR;
              case "lorry":         return LORRY;
              case "arrow.right":   return ARROW_RIGHT;
              case "arrow.left":    return ARROW_LEFT;
              case "new":           return NEW;
              case "search":        return SEARCH;
              case "wrench":        return WRENCH;
              case "edit":          return EDIT;
              case "atention":      return ATENTION;
              case "email":         return EMAIL;
              case "form":          return FORM;
              case "table":         return TABLE;
              case "lock":          return LOCK;
              case "pack":          return PACK;
              case "dock":          return DOCK;
              case "print":         return PRINT;
          }
      }

      return CANCEL;


  }


}
}